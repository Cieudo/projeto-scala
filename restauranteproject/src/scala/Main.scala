import scala.io.StdIn
import java.time.LocalDateTime
import models.{Pedido, Mesa}

object Main {
  def main(args: Array[String]): Unit = {
    var continua = true

    while (continua) {
      println("\nEscolha uma opção:")
      println("1. Adicionar Prato ao Cardapio")
      println("2. Adicionar cliente")
      println("3. Adicionar pedido ao Cliente")
      println("4. Exibir clientes")
      println("5. Exibir pedidos por id do Cliente")
      println("6. Exibir pedidos por id da Mesa do Cliente")
      println("7. Exibir Cardapio Disponpivel")
      val escolha = StdIn.readInt()

      escolha match {
        case 1 => BancoDeDados.adicionarPrato()
        case 2 => BancoDeDados.adicionarCliente()
        case 3 => BancoDeDados.adicionarPedido()
        case 4 => BancoDeDados.exibirClientes()
        case 5 =>
          println("Digite o identificador do cliente:")
          val clienteId = StdIn.readInt()

          val pedidosCliente = BancoDeDados.getPedidosPorCliente(clienteId)

          if (pedidosCliente.nonEmpty) {
            println(s"\nPedidos do cliente $clienteId:")
            pedidosCliente.foreach { case (pedido, nomePrato) =>
              println(s"ID: ${pedido.id}")
              println(s"Item: $nomePrato")
              println(s"Mesa: ${pedido.mesaId}")
              println(s"Data/Hora: ${pedido.dataHora}")
              println("---------------------------")
            }
          } else {
            println(s"Nenhum pedido encontrado para o cliente $clienteId.")
          }


        case 6 =>
          println("Digite o identificador da mesa:")
          val mesaId = StdIn.readInt()

          val pedidosMesa = BancoDeDados.recuperarPedidosPorMesa(mesaId)

          if (pedidosMesa.nonEmpty) {
            println(s"\nPedidos realizados na mesa $mesaId:")
            pedidosMesa.foreach { case (pedido, nomePrato) =>
              println(s"ID: ${pedido.id}")
              println(s"Item: $nomePrato")
              println(s"Cliente: ${pedido.clienteId}")
              println(s"Data/Hora: ${pedido.dataHora}")
              println("---------------------------")
            }
          } else {
            println(s"Nenhum pedido encontrado para a mesa $mesaId.")
          }

        case 7 =>
          val cardapio = BancoDeDados.recuperarCardapio()
          if (cardapio.nonEmpty) {
            println("\nCardápio disponível:")
            cardapio.foreach { prato =>
              println(s"ID: ${prato.id}")
              println(s"Nome: ${prato.nome}")
              println(s"Descrição: ${prato.descricao}")
              println(s"Categoria: ${prato.categoria}")
              println(s"Preço: ${prato.preco}")
              println("---------------------------")
            }
          } else {
            println("Nenhum prato cadastrado no cardápio.")
          }
        case _ => println("Opção inválida")
      }
      println("Deseja continuar? (S/N)")
      val resposta = StdIn.readLine().toUpperCase()
      continua = resposta == "S"
    }
  }
}
