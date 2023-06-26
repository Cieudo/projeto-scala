package scala
import scala.BancoDeDados.BancoDeDados.adicionarCliente
import scala.BancoDeDados.adicionarMesa
import scala.io.StdIn
object Main {

  def main(args: Array[String]): Unit = {

    var continua = true

    while (continua) {
      println("\nEscolha uma opção:")
      println("1. Adicionar prato")
      println("2. Adicionar mesa")
      println("3. Adicionar Cliente")

      val escolha = StdIn.readInt()

      escolha match {
        case 2 => adicionarMesa()
        case 3 => adicionarCliente()
        case _ => println("Opção inválida")
      }
    }
  }
}