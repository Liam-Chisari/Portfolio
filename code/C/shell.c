// SPDX-License-Identifier: BSD-3-Clause
#define _GNU_SOURCE

#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>

#include <fcntl.h>
#include <unistd.h>

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include "cmd.h"
#include "utils.h"

#define READ 0
#define WRITE 1
#define ERROR 2

static bool shell_cd(word_t *dir)
{
return get_word(dir) == NULL ? chdir(getenv("HOME")) : chdir(get_word(dir));
}

static bool cd_helper(simple_command_t *s)
{
int openFlags = O_WRONLY | O_CREAT | O_TRUNC;
int appendFlags = O_WRONLY | O_CREAT | O_APPEND;
// here we check if we have the same file for the standard output and err
if (get_word(s->out) != NULL && get_word(s->err) != NULL && strcmp(get_word(s->out), get_word(s->err)) == 0) {
if (s->io_flags == IO_REGULAR && open(get_word(s->out), openFlags, 0644) == -1) {
if (open(get_word(s->out), openFlags, 0644) == -1)
exit(-1);
}
} else {
// If we have a out file, we set the file descriptor for it depending on if we have
if (get_word(s->out) != NULL) {
if (s->io_flags == IO_REGULAR) {
if (open(get_word(s->out), openFlags, 0644) == -1)
exit(-1);
} else if (s->io_flags == IO_OUT_APPEND) {
if (open(get_word(s->out), appendFlags, 0644) == -1)
exit(-1);
}
}

                      
    // If we have a err file, we set the file descriptor for it depending on if we have
    if (get_word(s->err) != NULL) {
        if (s->io_flags == IO_REGULAR) {
            if (open(get_word(s->out),  openFlags, 0644) == -1)
                exit(-1);
        } else if (s->io_flags == IO_ERR_APPEND) {
            if (open(get_word(s->err), appendFlags, 0644) == -1)
                exit(-1);
        }
    }
}

// Here we call the shell_cd function and we check if it was successful or not
int ret = shell_cd(s->params);

if (ret == -1) {
    if (open(get_word(s->err), appendFlags, 0644) != -1) {
        FILE *errFile = fdopen(open(get_word(s->err), appendFlags, 0644), "w");

        fprintf(errFile, "%s\n", "Error at changing directory");
        fclose(errFile);
    } else {
        fprintf(stderr, "%s\n", "Error at changing directory");
    }
}

return ret;
                  

}
//the function that returns the arguments of the command
static int shell_exit(void)
{
close(READ);
close(WRITE);
close(ERROR);
return SHELL_EXIT;
}

// helper function that returns the arguments of the command
static int helper_funcation(simple_command_t *s, char *command)
{
int status, num_arg = 0;

                      
pid_t pid = fork();

DIE(pid < 0, "fork");

if (pid > 0) {
    waitpid(pid, &status, 0);

    if (WIFEXITED(status))
        return WEXITSTATUS(status);

    return 1;
    }
                  

// Here we set the file descriptors for the input, output and err files
int _fd = 0;

                      
int *fd = &_fd;

if (get_word(s->in) != NULL) {
    if ((open(get_word(s->in), O_RDONLY)) == -1 || dup2((open(get_word(s->in), O_RDONLY)), READ) == -1)
        exit(-1);
}
int openFlags = O_WRONLY | O_CREAT | O_TRUNC;
int appendFlags = O_WRONLY | O_CREAT | O_APPEND;
// If we have the same output and err file, we set the same file descriptor for them
if (get_word(s->out) != NULL && get_word(s->err) != NULL && strcmp(get_word(s->out), get_word(s->err)) == 0) {
    if (s->io_flags == IO_REGULAR) {
        *fd = open(get_word(s->out), openFlags, 0644);

        if ((open(get_word(s->out), openFlags, 0644)) == -1 || dup2((*fd), WRITE) == -1 || dup2((*fd), ERROR) == -1) {
            close(*fd);
            exit(-1);
        }
    }
} else {
    // If we have a out file, we set the file descriptor for it depending on if we have
    if (get_word(s->out) != NULL) {
        if (s->io_flags == IO_REGULAR) {
            if ((open(get_word(s->out), openFlags, 0644)) == -1 || dup2((open(get_word(s->out), openFlags, 0644)), WRITE) == -1)
                exit(-1);
        } else if (s->io_flags == IO_OUT_APPEND) {
            if ((open(get_word(s->out), appendFlags, 0644)) == -1 ||
            dup2((open(get_word(s->out), appendFlags, 0644)), WRITE) == -1)
                exit(-1);
        }
    }

    if (get_word(s->err) != NULL) {
        if (s->io_flags == IO_REGULAR) {
            if ((open(get_word(s->err), openFlags, 0644)) == -1 || dup2((open(get_word(s->err), openFlags, 0644)), ERROR) == -1)
                exit(-1);
        } else if (s->io_flags == IO_ERR_APPEND) {
            if ((open(get_word(s->err), appendFlags, 0644)) == -1 ||
            dup2((open(get_word(s->err), appendFlags, 0644)), ERROR) == -1)
                exit(-1);
        }
    }
}
// Here we get the number of arguments of the command
if (execvpe(command, get_argv(s, &num_arg), environ) == -1) {
    fprintf(stderr, "%s '%s'\n", "Execution failed for", command);
    exit(-1);
    }
return 0;
                  

}

static int parse_simple(simple_command_t *s, int level, command_t *father)
{
if (s == NULL || level < 0 || father == NULL)
return SHELL_EXIT;
// Here we check if we have a cd command or a exit command
if (strcmp(get_word(s->verb), "cd") == 0)
return cd_helper(s);
else if (strcmp(get_word(s->verb), "exit") == 0 || strcmp(get_word(s->verb), "quit") == 0)
return shell_exit();
// return enviroment variable
if (strchr(get_word(s->verb), '=') != 0)
return setenv(s->verb->string, get_word(s->verb->next_part->next_part), 1);
// Here we call the helper function that returns the arguments of the command
return helper_funcation(s, get_word(s->verb));
}

// Here we run the commands in parallel
static bool run_in_parallel(command_t *cmd1, command_t *cmd2, int level,
command_t *father)
{
DIE(cmd1 == NULL || cmd2 == NULL, "cmd1 or cmd2 is NULL");
DIE(father == NULL, "father is NULL");
DIE(level < 0, "level is negative");

                      
int status1;
// Here we create the first child of the process
pid_t pid1 = fork();

DIE(pid1 < 0, "fork");
    if (pid1 > 0) {
        // 2nd child of the process
        int status2;
        pid_t pid2 = fork();

        DIE(pid2 < 0, "fork");
            if (pid2 > 0) {
                waitpid(pid1, &status1, 0);
                waitpid(pid2, &status2, 0);

                if (WIFEXITED(status1) && WIFEXITED(status2))
                    return WEXITSTATUS(status1) | WEXITSTATUS(status2);

                return 1;
            }

            exit(parse_command(cmd1, level, father));
    } else {
        exit(parse_command(cmd2, level, father));
    }
return 0;
                  

}

static bool run_on_pipe(command_t *cmd1, command_t *cmd2, int level,
command_t *father)
{
DIE(cmd1 == NULL || cmd2 == NULL, "cmd1 or cmd2 is NULL");
DIE(father == NULL, "father is NULL");
DIE(level < 0, "level is negative");
// create the pipe
int pipe_fd[2];
int status = pipe(pipe_fd);

                      
DIE(status == -1, "pipe");

int status1;
pid_t pid1 = fork();

DIE(pid1 < 0, "fork");
    if (pid1 > 0) {
        int status2;
        pid_t pid2 = fork();

        DIE(pid2 < 0, "fork");
            if (pid2 > 0) {
                close(pipe_fd[0]);
                close(pipe_fd[1]);

                waitpid(pid1, &status1, 0);
                waitpid(pid2, &status2, 0);
                if (WIFEXITED(status2))
                    return WEXITSTATUS(status2);

                return 1;
            }
            close(pipe_fd[1]);

            if (dup2(pipe_fd[0], READ) == -1) {
                close(pipe_fd[0]);
                exit(-1);
            }
            close(pipe_fd[0]);
            exit(parse_command(cmd2, level, father));
    } else {
        close(pipe_fd[0]);

        if (dup2(pipe_fd[1], WRITE) == -1) {
            close(pipe_fd[1]);
            exit(-1);
        }
        close(pipe_fd[1]);
        exit(parse_command(cmd1, level, father));
    }
return 0;
                  

}
int parse_command(command_t *c, int level, command_t *father)
{
if (c == NULL || level < 0)
return SHELL_EXIT;

                      
if (c->op == OP_NONE)
    return parse_simple(c->scmd, ++level, c);

int ret = 0;

switch (c->op) {
case OP_SEQUENTIAL:
    ret = parse_command(c->cmd1, ++level, c);
    ret |= parse_command(c->cmd2, ++level, c);

    break;

case OP_PARALLEL:
    ret = run_in_parallel(c->cmd1, c->cmd2, ++level, c);
    break;

case OP_CONDITIONAL_NZERO:
    ret = parse_command(c->cmd1, ++level, c);
    if (ret != 0)
        ret = parse_command(c->cmd2, ++level, c);

    break;

case OP_CONDITIONAL_ZERO:
    ret = parse_command(c->cmd1, ++level, c);
    if (ret == 0)
        ret = parse_command(c->cmd2, ++level, c);

    break;

case OP_PIPE:
    ret = run_on_pipe(c->cmd1, c->cmd2, ++level, c);
    break;

default:
    return SHELL_EXIT;
}

return ret;
                  

}
