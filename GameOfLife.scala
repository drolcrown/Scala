/**
  * Created by aeron on 22/08/17.
  */
object GameOfLife extends App{
  var i = 0; var j = 0; val min = 0

  println("Digite a dimensão da matriz: ")
  var max = Console.readInt()

  val myMatrix = Array.ofDim[String](max, max)

  for (i <- min until  max){
    for(j <- min until max) {
      myMatrix(i)(j) = "x"
      printf(myMatrix(i)(j) + " ")
    }
    println()
  }

  def random(value : Int): Int ={
    val numberRandom = scala.util.Random

    numberRandom.nextInt(2) match {
      case 1 => value + 1
      case 2 => value - 1
    }
  }

  def createModel(row: Int, column : Int, iterator: Int): Unit = {
    if (iterator <= min) {
      var iterator = 1
    }
    (verifyBorder(row, column), iterator) match {
      case (true, _) => myMatrix(row)(column) = "o"; createModel(random(row), random(column), iterator+1)
      case (true, 3) => myMatrix(row)(column) = "o"
      case (false, _) => createModel(random(row), random(column), iterator-1)
    }
  }

  def verifyBorder(row: Int, column : Int): Boolean = {
    if ((row >= min && row < max) && (column >= min && column < max))
      true
    else
      false
  }

  def isAlive(row: Int, column : Int): Boolean = {
    if (myMatrix(row)(column) == "o")
      true
    else
      false
  }

  def countNeighbors(row: Int, column : Int): Int = {
    var neighbors = 0

    for (i <- -1 until 1){
      for (j <- -1 until 1){
        if (verifyBorder(i, j)){
          if (isAlive(i, j) && ((row != i) || (column != j))){
            neighbors += 1
          }
        }
      }
    }
    println("Vizinhos : " + neighbors)
    neighbors
  }

  println("Digite as coordenadas onde deseja iniciar o jogo: ")
  println("Linha: ")
  var row = Console.readInt()
  println("Coluna: ")
  var column = Console.readInt()

  for (i <- min to  max){
    for(j <- min to max) {
      myMatrix(i)(j) = "x"
      printf(myMatrix(i)(j) + " ")
    }
    println()
  }
}
