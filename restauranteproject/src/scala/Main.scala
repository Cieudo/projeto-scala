package scala

import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    var continua = true

    while (continua) {
      println("\nEscolha uma opção:")
      println("1. Adicionar prato")
      println("2. Adicionar mesa")
      println("3. Adicionar cliente")
      println("4. Adicionar pedido")
      println("5. Exibir clientes")
      val escolha = StdIn.readInt()

      escolha match {
        case 1 => BancoDeDados.adicionarPrato()
        case 2 => BancoDeDados.adicionarMesa()
        case 3 => BancoDeDados.adicionarCliente()
        case 4 => BancoDeDados.adicionarPedido()
        case 5 => BancoDeDados.exibirClientes()
        case _ => println("Opção inválida")
      }

      println("Deseja continuar? (S/N)")
      val resposta = StdIn.readLine().toUpperCase()
      continua = resposta == "S"
    }

    println("Digite o identificador do cliente:")
    val clienteId = StdIn.readInt()

    val pedidosCliente = BancoDeDados.getPedidosPorCliente(clienteId)

    if (pedidosCliente.nonEmpty) {
      println(s"\nPedidos do cliente $clienteId:")
      pedidosCliente.foreach { pedido =>
        println(s"ID: ${pedido.id}")
        println(s"Item: ${pedido.itemId}")
        println(s"Mesa: ${pedido.mesaId}")
        println(s"Data/Hora: ${pedido.dataHora}")
        println("---------------------------")
      }
    } else {
      println(s"Nenhum pedido encontrado para o cliente $clienteId.")
    }


  }
}
