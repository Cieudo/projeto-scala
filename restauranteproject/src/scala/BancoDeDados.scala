import scala.io.StdIn
import models.{Cliente, Mesa, Pedido, Prato}

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
    val novaMesa = Mesa(getNovoIdMesa(), capacidadeMaxima)
    mesa = novaMesa :: mesa
    println("Mesa adicionada com sucesso!")
  }

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

    println("Digite o e-mail do cliente:")
    val email = StdIn.readLine()

    val novoCliente = Cliente(getNovoIdCliente(), nome, telefone, email)
    clientes = novoCliente :: clientes

    println("Cliente adicionado com sucesso!")
  }

  def exibirClientes(): Unit = {
    println("\nClientes cadastrados:")
    clientes.foreach { cliente =>
      println(s"ID: ${cliente.id}")
      println(s"Nome: ${cliente.nome}")
      println(s"Telefone: ${cliente.telefone}")
      println(s"E-mail: ${cliente.email}")
      println("---------------------------")
    }
  }

  var cardapio: List[Prato] = List()
  var pedidos: List[Pedido] = List()
  var contadorPedido: Int = 1

  def getNovoIdPedido(): Int = {
    val novoId = contadorPedido
    contadorPedido += 1
    novoId
  }

  def adicionarPedido(): Unit = {
    println("Digite o identificador do item pedido:")
    val itemPedidoId = StdIn.readInt()

    println("Digite o número da mesa onde o pedido foi realizado:")
    val mesaId = StdIn.readInt()

    println("Digite o identificador do cliente que fez o pedido:")
    val clienteId = StdIn.readInt()

    println("Digite a data e horário do pedido:")
    val dataHora = StdIn.readLine()

    val novoPedido = Pedido(getNovoIdPedido(), itemPedidoId, mesaId, clienteId, dataHora)
    pedidos = novoPedido :: pedidos

    println("Pedido adicionado com sucesso!")
  }

  def getPedidosPorCliente(clienteId: Int): List[Pedido] = {
    pedidos.filter(_.clienteId == clienteId)
  }

  def recuperarPedidosPorMesa(mesaId: Int): List[Pedido] = {
    pedidos.filter(_.mesaId == mesaId)
  }


  def adicionarPrato(): Unit = {
    println("Digite o nome do prato ou bebida:")
    val nome = StdIn.readLine()

    println("Digite a descrição do prato:")
    val descricao = StdIn.readLine()

    println("Digite a categoria do prato:")
    val categoria = StdIn.readLine()

    println("Digite o preço do prato:")
    val preco = StdIn.readDouble()

    println("Prato adicionado com sucesso!")
  }
}
