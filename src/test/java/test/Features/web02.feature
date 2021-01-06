Feature: Web 02

  Background:
    Given Navegar o link "http://www.trivago.com.br"
    And Digitar "Natal" no campo de pesquisa
    And Clicar no botão pesquisa
    And Clicar no icone cancelar as datas
    And Selecionar no quarto individual
    And Selecionar a opção Ordenar apenas por Distância

  Scenario: Segundo item da listado hostéis
    Given Selecionar "2" item
    And Imprimir o nome do hotel
    And Imprimir a quantidade de estrela
    And Imprimir o nome do site da oferta
    And Imprimir o valor do quarto
    #And Clicar o localização
    #And Clicar o link para visualizar todas as comodidades
    #And Imprimir as Comodidades do quarto
    Then Mostrar informação do hotel

