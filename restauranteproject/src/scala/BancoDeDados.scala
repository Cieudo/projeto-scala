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

  def getNovoIdPrato(): Int = {
    val novoId = contadorPrato
    contadorPrato += 1
    novoId
  }
  var contadorPrato: Int = 1


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
    if (clientes.nonEmpty) {
      println("\nClientes cadastrados:")
      clientes.foreach { cliente =>
        println(s"ID: ${cliente.id}")
        println(s"Nome: ${cliente.nome}")
        println(s"Telefone: ${cliente.telefone}")
        println(s"E-mail: ${cliente.email}")
        println("---------------------------")
      }
    } else {
      println("Nenhum cliente cadastrado.")
    }
  }

  var cardapio: List[Prato] = List(
    Prato(getNovoIdPrato(), "Macarrão à Bolonhesa", "Macarrão com molho de tomate e carne moída", "Massas", 20.0),
    Prato(getNovoIdPrato(), "Frango Grelhado", "Peito de frango grelhado com acompanhamentos", "Pratos Principais", 25.0),
    Prato(getNovoIdPrato(), "Salada Caesar", "Salada com alface, frango, croutons e molho caesar", "Saladas", 15.0),
    Prato(getNovoIdPrato(), "Sorvete de Chocolate", "Sorvete cremoso sabor chocolate", "Sobremesas", 10.0),
    Prato(getNovoIdPrato(), "Coca-Cola", "Refrigerante sabor cola", "Bebidas", 5.0),
    Prato(getNovoIdPrato(), "Suco de Maracujá", "Suco natural da fruta", "Bebidas", 7.0),
  )
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

  def getPedidosPorCliente(clienteId: Int): List[(Pedido, String)] = {
    pedidos
      .filter(_.clienteId == clienteId)
      .flatMap { pedido =>
        val prato = cardapio.find(_.id == pedido.itemId)
        prato.map(p => (pedido, p.nome))
      }
  }


  def recuperarPedidosPorMesa(mesaId: Int): List[(Pedido, String)] = {
    pedidos
      .filter(_.mesaId == mesaId)
      .flatMap { pedido =>
        val prato = cardapio.find(_.id == pedido.itemId)
        prato.map(p => (pedido, p.nome))
      }
  }


  def recuperarCardapio(): List[Prato] = {
    cardapio
  }

  def adicionarPrato(): Unit = {
    println("Digite o nome do prato:")
    val nome = StdIn.readLine()

    println("Digite a descrição do prato:")
    val descricao = StdIn.readLine()

    println("Digite a categoria do prato:")
    val categoria = StdIn.readLine()

    println("Digite o preço do prato:")
    val preco = StdIn.readDouble()

    val novoPrato = Prato(getNovoIdPrato(), nome, descricao, categoria, preco)
    cardapio = novoPrato :: cardapio

    println("Prato adicionado com sucesso!")
  }
}
