/**
  * Created by aeron on 19/08/17.
  */


class Aluno extends Pessoa("Rafael", 1232133){
  val aluno = new Pessoa("Rafael2", 123213322)

  println("nome do aluno = " + aluno.getNome() + "Matricula = " + aluno.getMatricula())
}


class Pessoa(name:String, mat:Int){
  var nome:String
  var matricula:Int

  def getNome(){this.nome = name}
  def getMatricula(){this.matricula = mat}
}