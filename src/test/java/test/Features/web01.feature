Feature: Web 01

  Scenario: Automatizar o teste no site discourse
    Given Navegar o link "https://www.discourse.org/"
    And Clicar Demo link
    And Tab está aberta
    And Fazer scroll até o final da página
    Then Mostrar a lista do topicos fechados
    And Mostrar quantidades tópicos em cada categoria
    And Mostrar qual titulo do tópico mais visualizados

