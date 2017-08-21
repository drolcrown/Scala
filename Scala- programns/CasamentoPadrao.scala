object PatternMatching{
    def casamento(arg: Any) = {
        arg match {
            case 1 => println("E um numero")
            case "1" => println("E uma string")
            case true => println("Verdadeiro")
        }
    }
}