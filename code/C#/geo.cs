using System;

class Program
{
    static void Main()
    {
        bool exit = false;

        while (!exit)
        {
            Console.WriteLine("Select a shape:");
            Console.WriteLine("1. Circle");
            Console.WriteLine("2. Square");
            Console.WriteLine("3. Rectangle");
            Console.WriteLine("4. Equilateral Triangle");
            Console.WriteLine("5. Diamond");
            Console.WriteLine("6. Hexagon");
            Console.WriteLine("7. Pentagon");
            Console.WriteLine("8. Octagon");
            Console.WriteLine("9. Nonagon");
            Console.WriteLine("10. Exit");

            int choice = int.Parse(Console.ReadLine());

            switch (choice)
            {
                case 1: // Circle
                    double radius = double.Parse(Console.ReadLine());
                    double area = 3.14159 * radius * radius;
                    double circumference = 2 * 3.14159 * radius;
                    Console.WriteLine("Area: " + area);
                    Console.WriteLine("Circumference: " + circumference);
                    Console.WriteLine("Number of corners: 0");
                    Console.WriteLine("Number of edges: 1");
                    Console.WriteLine("Number of faces: 1");
                    Console.WriteLine("Diameter: " + 2 * radius);
                    break;
                case 2: // Square
                    double side = double.Parse(Console.ReadLine());
                    double areaSquare = side * side;
                    double perimeterSquare = 4 * side;
                    Console.WriteLine("Area: " + areaSquare);
                    Console.WriteLine("Perimeter: " + perimeterSquare);
                    Console.WriteLine("Number of corners: 4");
                    Console.WriteLine("Number of edges: 4");
                    Console.WriteLine("Number of faces: 1");
                    Console.WriteLine("Diagonal: " + Math.Sqrt(2) * side);
                    break;
                case 3: // Rectangle
                    double length = double.Parse(Console.ReadLine());
                    double width = double.Parse(Console.ReadLine());
                    double areaRectangle = length * width;
                    double perimeterRectangle = 2 * (length + width);
                    Console.WriteLine("Area: " + areaRectangle);
                    Console.WriteLine("Perimeter: " + perimeterRectangle);
                    Console.WriteLine("Number of corners: 4");
                    Console.WriteLine("Number of edges: 4");
                    Console.WriteLine("Number of faces: 1");
                    Console.WriteLine("Diagonal: " + Math.Sqrt(length * length + width * width));
                    break;
                case 4: // Equilateral Triangle
                    double baseTriangle = double.Parse(Console.ReadLine());
                    double heightTriangle = Math.Sqrt(3) / 2 * baseTriangle;
                    double areaTriangle = 0.5 * baseTriangle * heightTriangle;
                    double perimeterTriangle = 3 * baseTriangle;
                    Console.WriteLine("Area: " + areaTriangle);
                    Console.WriteLine("Perimeter: " + perimeterTriangle);
                    Console.WriteLine("Number of corners: 3");
                    Console.WriteLine("Number of edges: 3");
                    Console.WriteLine("Number of faces: 1");
                    Console.WriteLine("Height: " + heightTriangle);
                    break;
                case 5: // Diamond
                    double sideDiamond = double.Parse(Console.ReadLine());
                    double areaDiamond = (2.5 * sideDiamond * sideDiamond) / 2;
                    double perimeterDiamond = 4 * sideDiamond;
                    Console.WriteLine("Area: " + areaDiamond);
                    Console.WriteLine("Perimeter: " + perimeterDiamond);
                    Console.WriteLine("Number of corners: 4");
                    Console.WriteLine("Number of edges: 4");
                    Console.WriteLine("Number of faces: 1");
                    Console.WriteLine("Diagonal: " + Math.Sqrt(2) * sideDiamond);
                    break;
                case 6: // Hexagon
                    double sideHexagon = double.Parse(Console.ReadLine());
                    double areaHexagon = (3 * Math.Sqrt(3) * sideHexagon * sideHexagon) / 2;
                    double perimeterHexagon = 6 * sideHexagon;
                    Console.WriteLine("Area: " + areaHexagon);
                    Console.WriteLine("Perimeter: " + perimeterHexagon);
                    Console.WriteLine("Number of corners: 6");
                    Console.WriteLine("Number of edges: 6");
                    Console.WriteLine("Number of faces: 1");
                    Console.WriteLine("Diagonal: " + sideHexagon * Math.Sqrt(3) / 2);
                    break;
                case 7: // Pentagon
                    double sidePentagon = double.Parse(Console.ReadLine());
                    double areaPentagon = (5 * sidePentagon * sidePentagon) / (4 * Math.Tan(Math.PI / 5));
                    double perimeterPentagon = 5 * sidePentagon;
                    Console.WriteLine("Area: " + areaPentagon);
                    Console.WriteLine("Perimeter: " + perimeterPentagon);
                    Console.WriteLine("Number of corners: 5");
                    Console.WriteLine("Number of edges: 5");
                    Console.WriteLine("Number of faces: 1");
                    Console.WriteLine("Diagonal: " + (sidePentagon * Math.Sqrt(5 - 2 * Math.Sqrt(5))) / 2);
                    break;
                case 8: // Octagon
                    double sideOctagon = double.Parse(Console.ReadLine());
                    double areaOctagon = (2 * sideOctagon * sideOctagon * (Math.Sqrt(2) - 1)) / 2;
                    double perimeterOctagon = 8 * sideOctagon;
                    Console.WriteLine("Area: " + areaOctagon);
                    Console.WriteLine("Perimeter: " + perimeterOctagon);
                    Console.WriteLine("Number of corners: 8");
                    Console.WriteLine("Number of edges: 8");
                    Console.WriteLine("Number of faces: 1");
                    Console.WriteLine("Diagonal: " + sideOctagon * Math.Sqrt(2 - Math.Sqrt(2)));
                    break;
                case 9: // Nonagon
                    double sideNonagon = double.Parse(Console.ReadLine());
                    double areaNonagon = (3 * sideNonagon * sideNonagon * (Math.Sqrt(3) - 1)) / 2;
                    double perimeterNonagon = 9 * sideNonagon;
                    Console.WriteLine("Area: " + areaNonagon);
                    Console.WriteLine("Perimeter: " + perimeterNonagon);
                    Console.WriteLine("Number of corners: 9");
                    Console.WriteLine("Number of edges: 9");
                    Console.WriteLine("Number of faces: 1");
                    Console.WriteLine("Diagonal: " + sideNonagon * Math.Sqrt(3 - Math.Sqrt(3)));
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    Console.WriteLine("Invalid choice.");
                    break;
            }
        }
    }
}
