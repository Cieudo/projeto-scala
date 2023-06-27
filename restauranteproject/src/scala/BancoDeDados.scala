import scala.io.StdIn
import scala.io.Source
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

  def carregarPratos(): List[Prato] = {
    val arquivo = Source.fromFile("prato.txt")
    val linhas = arquivo.getLines().toList
    arquivo.close()

    var pratos: List[Prato] = List.empty
    var pratoBuilder: Option[Prato.Builder] = None

    for (linha <- linhas) {
      if (linha.startsWith("ID:")) {
        val pratoId = linha.substring(3).toInt
        pratoBuilder = Some(Prato.Builder(pratoId))
      } else if (linha.startsWith("Nome:")) {
        pratoBuilder.foreach(_.nome(linha.substring(5)))
      } else if (linha.startsWith("Descrição:")) {
        pratoBuilder.foreach(_.descricao(linha.substring(10)))
      } else if (linha.startsWith("Categoria:")) {
        pratoBuilder.foreach(_.categoria(linha.substring(11)))
      } else if (linha.startsWith("Preço:")) {
        val preco = linha.substring(6).toDouble
        pratoBuilder.foreach(_.preco(preco))
        pratoBuilder.foreach { builder =>
          pratos = builder.build() :: pratos
        }
        pratoBuilder = None
      }
    }

    pratos.reverse
  }

  def carregarClientes(): List[Cliente] = {
    val arquivo = Source.fromFile("clientes.txt")
    val linhas = arquivo.getLines().toList
    arquivo.close()

    var clientes: List[Cliente] = List.empty
    var clienteBuilder: Option[Cliente.Builder] = None

    for (linha <- linhas) {
      if (linha.startsWith("ID:")) {
        val clienteId = linha.substring(3).toInt
        clienteBuilder = Some(Cliente.Builder(clienteId))
      } else if (linha.startsWith("Nome:")) {
        clienteBuilder.foreach(_.nome(linha.substring(5)))
      } else if (linha.startsWith("Telefone:")) {
        clienteBuilder.foreach(_.telefone(linha.substring(9)))
      } else if (linha.startsWith("E-mail:")) {
        clienteBuilder.foreach(_.email(linha.substring(7)))
        clienteBuilder.foreach { builder =>
          clientes = builder.build() :: clientes
        }
        clienteBuilder = None
      }
    }
    clientes.reverse
  }

}
