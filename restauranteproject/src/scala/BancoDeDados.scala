package scala
import scala.io.StdIn
import scala.models.{Cliente, Mesa, Pedido, Prato}

object BancoDeDados {
  var mesa: List[Mesa] = List()
  var contadorMesa: Int = 1
  def getNovoIdMesa(): Int = {
    val novoId = contadorMesa
    contadorMesa += 1
    novoId
  }

  def adicionarMesa(): Unit = {
    println("Digite a capacidade máxima da mesa:")
    val capacidadeMaxima = StdIn.readInt()
    val novaMesa = models.Mesa(getNovoIdMesa(), capacidadeMaxima)
    mesa = novaMesa :: mesa

    println("Mesa adicionada com sucesso!")
  }

  object BancoDeDados {
    var clientes: List[Cliente] = List()
    var contadorCliente: Int = 1

    def getNovoIdCliente(): Int = {
      val novoId = contadorCliente
      contadorCliente += 1
      novoId
    }

    def adicionarCliente(): Unit = {
      println("Digite o nome completo do cliente:")
      val nome = StdIn.readLine()

      println("Digite o número de telefone do cliente:")
      val telefone = StdIn.readLine()

      val novoCliente = models.Cliente(getNovoIdCliente(), nome, telefone)
      clientes = novoCliente :: clientes

      println("Cliente adicionado com sucesso!")
    }
  }

}

