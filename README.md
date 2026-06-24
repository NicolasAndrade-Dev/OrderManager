# 📦 OrderManager

## Sistema de Gestão para Representantes Comerciais

O **OrderManager** é um aplicativo Android desenvolvido para auxiliar representantes comerciais e pequenas empresas no gerenciamento de clientes, produtos, estoque e pedidos diretamente pelo celular.

O sistema foi criado com foco em praticidade, organização e controle das vendas realizadas em visitas comerciais, permitindo que vendedores registrem pedidos rapidamente e que administradores acompanhem as operações da empresa.

---

# 🎯 Objetivo do Projeto

O objetivo do OrderManager é substituir anotações em papel e planilhas dispersas por um sistema centralizado que permita:

* Cadastro de clientes
* Cadastro de produtos
* Controle de estoque
* Registro de pedidos
* Controle de usuários
* Gestão de vendedores
* Organização das informações comerciais

---

# 👨‍💼 Cenário de Uso

Imagine um representante comercial visitando clientes durante o dia.

Ao chegar em uma empresa, ele pode:

1. Consultar clientes cadastrados
2. Ver os produtos disponíveis
3. Conferir o estoque
4. Registrar um novo pedido
5. Calcular automaticamente o valor total da venda

Essas informações ficam armazenadas localmente no dispositivo, facilitando o gerenciamento e acompanhamento das operações comerciais.

---

# 🏗️ Arquitetura Utilizada

O projeto foi desenvolvido seguindo o padrão **MVVM (Model - View - ViewModel)**.

## Estrutura

```text
UI (Compose)
↓
ViewModel
↓
Repository
↓
Room Database
```

### Camadas

#### UI

Responsável pelas telas e interação com o usuário.

#### ViewModel

Responsável pela lógica de negócio e gerenciamento de estados.

#### Repository

Responsável pela comunicação entre ViewModel e banco de dados.

#### Room Database

Responsável pelo armazenamento local dos dados.

---

# 📱 Funcionalidades

## 🔐 Login

* Tela de autenticação
* Validação de usuário e senha
* Controle de acesso ao sistema

Usuário padrão:

```text
Usuário: admin
Senha: 123
```

---

## 👥 Gestão de Clientes

Permite:

* Cadastrar clientes
* Editar clientes
* Excluir clientes
* Visualizar lista de clientes

### Campos

* Nome
* Telefone
* E-mail
* Cidade

### Validações

* Campos obrigatórios
* Validação de e-mail
* Validação de telefone

---

## 📦 Gestão de Produtos

Permite:

* Cadastrar produtos
* Editar produtos
* Excluir produtos
* Visualizar estoque

### Campos

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

* Criar pedidos
* Selecionar cliente
* Selecionar produto
* Informar quantidade
* Calcular valor total automaticamente
* Excluir pedidos

### Informações registradas

* Cliente
* Produto
* Quantidade
* Data
* Hora
* Valor total

---

## ⚙️ Configurações

Permite:

* Alternar entre tema claro e escuro
* Persistência das configurações utilizando DataStore

---

## 👨‍💻 Gestão de Usuários

Permite ao administrador:

* Cadastrar vendedores
* Gerenciar usuários do sistema
* Controlar permissões

Perfis disponíveis:

### ADMIN

Possui acesso total ao sistema.

### VENDEDOR

Possui acesso operacional para utilização do sistema.

---

## 📊 Dashboard

Tela inicial contendo resumo do sistema:

* Quantidade de clientes
* Quantidade de produtos
* Quantidade de pedidos

---

## 🚀 Splash Screen

Ao iniciar o aplicativo é exibida uma tela de carregamento personalizada antes do acesso ao login.

---

# 🗄️ Banco de Dados

O aplicativo utiliza:

* Room Database

Entidades:

```text
UserEntity
ClientEntity
ProductEntity
OrderEntity
```

---

# 🎨 Interface

O projeto utiliza:

* Jetpack Compose
* Material Design 3
* Dark Theme
* Light Theme
* Navegação moderna

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

## Gerenciamento de Estado

* StateFlow
* Coroutines

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
* Persistência local com Room
* Configurações salvas com DataStore
* Dashboard de indicadores
* Splash Screen
* Tema escuro
* Controle de usuários
* Interface moderna em Jetpack Compose
* Organização profissional do código

---

# 📚 Aprendizados Obtidos

Durante o desenvolvimento deste projeto foram aplicados conhecimentos de:

* Programação Android
* Kotlin
* Jetpack Compose
* Arquitetura MVVM
* Banco de Dados Room
* DataStore
* Navegação entre telas
* Gerenciamento de estado
* Versionamento com Git e GitHub

---

# 🔮 Melhorias Futuras

* Relatórios de vendas
* Exportação para PDF
* Busca avançada
* Dashboard financeiro
* Sincronização em nuvem
* Multiempresa
* Controle completo de permissões
* Integração com APIs

---

# 👨‍🎓 Projeto Acadêmico

Projeto desenvolvido para fins educacionais com o objetivo de aplicar conceitos modernos de desenvolvimento Android utilizando Kotlin, Jetpack Compose e arquitetura MVVM.

---

# 📷 Demonstração

Adicionar capturas de tela do aplicativo:

* Login
* Home
* Clientes
* Produtos
* Pedidos
* Configurações
* Gestão de Usuários

---

# 🔗 Repositório

Disponível no GitHub para fins de estudo, evolução e demonstração do projeto.

---

## 📦 OrderManager

**Sistema de Gestão para Representantes Comerciais**

Desenvolvido utilizando Kotlin + Jetpack Compose + MVVM + Room Database.
