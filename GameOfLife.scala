/**
  * Created by aeron on 22/08/17.
  */
object GameOfLife extends App{
  var i = 0; var j = 0; val min = 0
  var listTuple = new Array[(Int, Int)](8)

  println("Digite a dimensão da matriz: ")
  val max = Console.readInt()

  var myMatrix = Array.ofDim[String](max, max)
  var myCopyMatrix = Array.ofDim[String](max, max)

  def impress() : Unit = {
    for (i <- min until max){
      for(j <- min until max) {
        myMatrix(i)(j) = "x"
        printf(myMatrix(i)(j) + " ")
      }
      println()
    }
  }

  def impress(matrix: Array[Array[String]]) : Int = {
    var cells = 0

    for (i <- min until max){
      for(j <- min until max) {
        printf(matrix(i)(j) + " ")
        if(myMatrix(i)(j) == "o"){
          cells += 1
        }
      }
      println()
    }
    cells
  }

  def random(value : Int): Int ={
    val numberRandom = scala.util.Random
    numberRandom.nextInt(3) match {
      case 1 => if (value + 1 >= max){random(value)} else {value + 1}
      case 2 => if (value - 1 < min){random(value)} else {value - 1}
      case _ => value
    }
  }

  def createModel(row: Int, column : Int, iterator: Int): Unit = {
    if (iterator <= min){
      var iterator = 1
    }
    (verifyBorder(row, column), iterator) match {
      case (true, 6) => if (myMatrix(row)(column) == "o"){createModel(random(row), random(column), iterator)}
                       else {myMatrix(row)(column) == "o"}
      case (true, _) => if (myMatrix(row)(column) == "o"){createModel(random(row), random(column), iterator)}
                        else{ myMatrix(row)(column) = "o"; createModel(random(row), random(column), iterator+1)}
      case (false, _) => createModel(random(row), random(column), iterator-1)
    }
    copyMatrix(myCopyMatrix, myMatrix)
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

    for (i <- (-1) to 1){
      for (j <- (-1) to 1){
        if (verifyBorder(row+i, column+j)){
          if (isAlive(row+i, column+j)) {
            listTuple(neighbors) = (row+i, column+j)
            neighbors += 1
          }
        }
      }
    }
    if(neighbors-1 < 0) {
      neighbors = 0
    }else {
      //println("Vizinhos : " + (neighbors - 1))
      neighbors -= 1
    }
    neighbors
  }

  def rulesOfLife(): Unit = {
    var change = false; var iterator = impress(myMatrix)

    while (iterator != 0) {
      for (row <- min until max){
        for(column <- min until max) {
          var neighbors = countNeighbors(row, column)
          //Todo - Qualquer célula viva com menos de dois vizinhos vivos morre de solidão.
          if (isAlive(row, column) && (neighbors < 2)) {
            myCopyMatrix(row)(column) = "x"
            change = true
          }
          //Todo - Qualquer célula viva com mais de três vizinhos vivos morre de superpopulação.
          if (isAlive(row, column) && (neighbors > 3)) {
            myCopyMatrix(row)(column) = "x"
            change = true
          }
          //Todo - Qualquer célula morta com exatamente três vizinhos vivos se torna uma célula viva.
          if (!isAlive(row, column) && (neighbors == 3)) {
            myCopyMatrix(row)(column) = "o"
            change = true
          }
          //Todo - Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.
          if (isAlive(row, column) && (neighbors == 2 || neighbors == 3)) {
            myCopyMatrix(row)(column) = "o"
            change = true
          }
          if(change) {
            copyMatrix(myMatrix, myCopyMatrix)
            iterator = impress(myMatrix)
            println("--------")
            change = false
          }
        }
      }
    }
  }

  def copyMatrix(matrix: Array[Array[String]], matrixCopy: Array[Array[String]]) : Unit ={
    for (i <- min until max){
      for(j <- min until max) {
        matrix(i)(j) = matrixCopy(i)(j)
      }
    }
  }

  impress()
  println("Digite as coordenadas onde deseja iniciar o jogo: ")
  println("Linha: ")
  var row = Console.readInt()-1
  println("Coluna: ")
  var column = Console.readInt()-1

  createModel(row, column, 1)
  rulesOfLife()
}
