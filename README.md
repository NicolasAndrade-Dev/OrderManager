# 📦 OrderManager

## Sistema de Gestão para Representantes Comerciais

O **OrderManager** é um aplicativo Android desenvolvido para auxiliar representantes comerciais e pequenas empresas no gerenciamento de clientes, produtos, estoque, pedidos e usuários diretamente pelo celular.

O sistema foi projetado para tornar o processo comercial mais organizado, reduzindo o uso de anotações em papel e facilitando o acompanhamento das vendas realizadas pelos vendedores durante visitas a clientes.

---

# 🎯 Objetivo do Projeto

O principal objetivo do OrderManager é oferecer uma solução simples e eficiente para o gerenciamento de operações comerciais, permitindo:

* Cadastro de clientes
* Cadastro de produtos
* Controle de estoque
* Registro de pedidos
* Controle de usuários
* Gestão de vendedores
* Organização das informações comerciais

---

# 👨‍💼 Cenário de Aplicação

O sistema foi idealizado para o contexto de **Representantes Comerciais**.

### Fluxo de Utilização

#### Administrador

* Cadastra vendedores
* Gerencia usuários do sistema
* Cadastra produtos
* Controla estoque
* Acompanha os pedidos realizados

#### Vendedor

* Consulta clientes
* Consulta produtos
* Verifica estoque disponível
* Registra novos pedidos
* Calcula automaticamente o valor da venda

---

# 🏗️ Arquitetura Utilizada

O projeto foi desenvolvido utilizando a arquitetura **MVVM (Model - View - ViewModel)**.

### Estrutura

```text
UI (Jetpack Compose)
↓
ViewModel
↓
Repository
↓
Room Database
```

### Benefícios da Arquitetura

* Separação de responsabilidades
* Maior organização do código
* Facilidade de manutenção
* Escalabilidade do sistema
* Melhor gerenciamento de estados

---

# 📱 Funcionalidades

## 🔐 Login

Permite autenticação dos usuários cadastrados.

### Usuário padrão

```text
Usuário: admin
Senha: 123
```

### Recursos

* Validação de credenciais
* Controle de acesso
* Diferenciação entre perfis

---

## 👥 Gestão de Clientes

Permite:

* Cadastrar clientes
* Editar clientes
* Excluir clientes
* Consultar clientes

### Dados cadastrados

* Nome
* Telefone
* E-mail
* Cidade

### Validações

* Campos obrigatórios
* E-mail válido
* Telefone válido

---

## 📦 Gestão de Produtos

Permite:

* Cadastrar produtos
* Editar produtos
* Excluir produtos
* Consultar estoque

### Dados cadastrados

* Nome
* Descrição
* Preço
* Quantidade em estoque

### Validações

* Campos obrigatórios
* Preço válido
* Estoque válido

---

## 🛒 Gestão de Pedidos

Permite:

* Registrar pedidos
* Selecionar cliente
* Selecionar produto
* Informar quantidade
* Calcular automaticamente o valor total
* Excluir pedidos

### Informações registradas

* Cliente
* Produto
* Quantidade
* Data
* Hora
* Valor total

---

## 👨‍💻 Gestão de Usuários

Permite ao administrador:

* Cadastrar vendedores
* Excluir vendedores
* Gerenciar acessos ao sistema

### Perfis

#### ADMIN

Possui acesso completo ao sistema.

#### VENDEDOR

Possui acesso operacional para utilização das funcionalidades comerciais.

---

## 📊 Dashboard

Tela inicial contendo indicadores do sistema:

* Total de clientes
* Total de produtos
* Total de pedidos

---

## ⚙️ Configurações

Permite:

* Ativar tema claro
* Ativar tema escuro
* Salvar preferências utilizando DataStore

---

## 🚀 Splash Screen

Ao iniciar o aplicativo, uma Splash Screen personalizada é exibida antes da tela de login.

---

# 🗄️ Banco de Dados

O sistema utiliza **Room Database** para armazenamento local.

### Entidades

```text
UserEntity
ClientEntity
ProductEntity
OrderEntity
```

---

# 🎨 Interface

Desenvolvida utilizando:

* Jetpack Compose
* Material Design 3
* Tema Claro
* Tema Escuro
* Componentes modernos do Android

---

# 🛠️ Tecnologias Utilizadas

## Linguagem

* Kotlin

## Interface

* Jetpack Compose
* Material 3

## Arquitetura

* MVVM

## Persistência

* Room Database
* DataStore

## Navegação

* Navigation Compose

## Programação Assíncrona

* Kotlin Coroutines
* StateFlow

## Controle de Versão

* Git
* GitHub

---

# 📂 Estrutura do Projeto

```text
com.example.ordermanager

├── data
│   ├── dao
│   ├── database
│   ├── datastore
│   ├── entity
│   └── repository
│
├── navigation
│
├── ui
│   ├── screens
│   ├── theme
│   └── viewmodel
│
└── MainActivity
```

---

# 💡 Diferenciais do Projeto

* Arquitetura MVVM
* Interface moderna em Jetpack Compose
* Banco de dados Room
* Persistência de configurações com DataStore
* Dashboard com indicadores
* Controle de usuários
* Gestão de vendedores
* Tema claro e escuro
* Navegação estruturada
* Organização profissional do código

---

# 📚 Aprendizados Obtidos

Durante o desenvolvimento foram aplicados conhecimentos relacionados a:

* Desenvolvimento Android
* Kotlin
* Jetpack Compose
* Arquitetura MVVM
* Room Database
* DataStore
* Navigation Compose
* StateFlow
* Coroutines
* Git e GitHub
* Boas práticas de desenvolvimento

---

# 🔮 Melhorias Futuras

* Relatórios de vendas
* Exportação para PDF
* Busca avançada
* Dashboard financeiro
* Integração com APIs externas
* Sincronização em nuvem
* Controle avançado de permissões
* Multiempresa

---

# 👨‍🎓 Informações Acadêmicas

## Instituição

Instituto Federal de Educação, Ciência e Tecnologia de São Paulo

**IFSP – Campus Capivari**

---

## Professor Responsável

**Edivaldo Serafim**

E-mail institucional:

[eserafim@ifsp.edu.br](mailto:eserafim@ifsp.edu.br)

---

## Desenvolvedor

**Nicolas De Azevedo Andrade Santana**

Responsável por:

* Planejamento do projeto
* Modelagem do sistema
* Desenvolvimento Android
* Implementação da arquitetura MVVM
* Criação da interface
* Banco de dados Room
* Implementação do DataStore
* Navegação entre telas
* Testes e validações
* Documentação técnica
* Controle de versão com Git e GitHub

---

# 🏆 Resultado Final

O OrderManager entrega uma solução funcional para gestão comercial, permitindo o gerenciamento de clientes, produtos, pedidos e vendedores em um ambiente moderno e organizado.

O projeto foi desenvolvido com foco educacional, aplicando conceitos atuais do ecossistema Android e simulando um cenário real de utilização por representantes comerciais.

---

# 📷 Demonstração

Adicionar capturas de tela:

* Login
* Home
* Clientes
* Produtos
* Pedidos
* Usuários
* Configurações
* Splash Screen

---

## 📦 OrderManager v1.0

**Sistema de Gestão para Representantes Comerciais**

Desenvolvido por **Nicolas De Azevedo Andrade Santana**

IFSP Campus Capivari – 2026

Kotlin • Jetpack Compose • MVVM • Room Database • DataStore
