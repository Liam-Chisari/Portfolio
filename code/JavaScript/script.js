function validateForm() {
    // Get the form elements
    const form = document.getElementById("contact-form");
    const name = document.getElementById("name").value;
   const email = document.getElementById("email").value;
   const message = document.getElementById("message").value;
  
  //Validation 
   if (name.trim() === "") {
     alert("Name is required.");
     return false;
   } 
   else if (email.trim() === "") {
     alert("Email is required.");
     return false;
   } 
   else if (message.trim() === "") {
    alert("Message is required.");
     return false;
   }
  
 
  //creating mailto link
  const mailToLink = `mailto:test@footballscores.org?subject=${encodeURIComponent(name)}&body=${encodeURIComponent(message)}`;

  //Redirect to mailToLink
  window.location.href = mailToLink;
  return true;
}  
