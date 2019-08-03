var inicio = new Vue({
	el:"#inicio",
	props: ['value'],
    data: function() {
		return {
			aba: undefined,
			produto: {},
			listaProdutos: [],
			listaProdutosHeader: [
				{sortable: false, key: "nome", label:"Nome"},
				{sortable: false, key: "fabricante.nome", label:"Fabricante"},
				{sortable: false, key: "volume", label:"Volume"},
				{sortable: false, key: "unidade", label:"Unidade"},
				{sortable: false, key: "estoque", label:"Estoque"}
			],
			fabricante: {},
			listaFabricantes: [],
			listaFabricantesHeader: [
				{ sortable: false, key: "id", label: "Id" },
				{ sortable: false, key: "nome", label: "Nome" }
			],
			formPreenchido: false
		}
    },
    created: function(){
        let vm =  this;
		vm.buscaProdutos();
		vm.buscaFabricantes();
		vm.setNavBar("saldacao");
    },
    methods:{
		setNavBar: function(s) {
			this.aba = s;
		},
        buscaProdutos: function(){
			const vm = this
			axios.get("/mercado/rest/produtos")
			.then(response => {
				vm.listaProdutos = response.data;
			})
			.catch(function (error) {
				alert("Erro interno. Não foi possivel listar os produtos");
			})
			.finally(function() {
			});
		},
		inserirProduto: function() {
			const vm = this
			axios.post("/mercado/rest/produtos", this.produto)
				.then(response => {
					alert("Produto cadastrado");
				})
				.catch(function (error) {
					console.log(error);
					alert("Erro ao cadastrar o produto ");
				})
				.finally(function () {
					vm.buscaProdutos();
					vm.limparProduto();
				});
		},
		deletarProduto: function (id) {
			const vm = this;
			axios.delete("/mercado/rest/produtos/" + id)
				.then(response => {
					alert("Produto removido com sucesso!");
				})
				.catch(function (error) {
					console.log(error);
					alert("Erro ao remover produto");
				}).finally(function () {
					vm.buscaProdutos();
				});
		},
		editarProduto: function (id) {
			const vm = this;
			axios.get("/mercado/rest/produtos/produto/" + id)
				.then(function (response) {
					vm.produto = response.data;
					vm.formPreenchido = true;
				})
				.catch(function (error) {
					console.log(error);
					alert("Erro ao editar produto.");
				});
		},
		salvarEdicao: function () {
			const vm = this;
			axios.put("/mercado/rest/produtos/" + this.produto.id, this.produto)
				.then(function (response) {
					alert("Produto editado com sucesso.");
				})
				.catch(function (error) {
					alert("Erro ao editar produto.");
				})
				.finally(function () {
					vm.produto = {};
					vm.buscaProdutos();
					vm.formPreenchido = false
				})
		},

		buscaFabricantes: function () {
			const vm = this
			axios.get("/mercado/rest/fabricantes")
				.then(response => {
					vm.listaFabricantes = response.data;
				})
				.catch(function (error) {
					alert("Erro interno. Não foi possivel listar os fabricantes");
				})
				.finally(function () {
				});
		},
		inserirFabricante: function () {
			const vm = this
			axios.post("/mercado/rest/fabricantes", this.fabricante)
				.then(response => {
					alert("Fabricante cadastrado");
				})
				.catch(function (error) {
					console.log(error);
					alert("Erro ao cadastrar o fabricante ");
				})
				.finally(function () {
					vm.buscaFabricantes();
					vm.limparFabricante();
				});
		},
		deletarFabricante: function (id) {
			const vm = this;
			axios.delete("/mercado/rest/fabricantes/" + id)
				.then(response => {
					alert("Fabricante removido com sucesso!");
				})
				.catch(function (error) {
					console.log(error);
					alert("Erro ao remover fabricante");
				}).finally(function () {
					vm.buscaFabricantes();
				});
		},
		editarFabricante: function (id) {
			const vm = this;
			axios.get("/mercado/rest/fabricantes/fabricante/" + id)
				.then(function (response) {
					vm.fabricante = response.data;
					vm.formPreenchido = true;
				})
				.catch(function (error) {
					console.log(error);
					alert("Erro ao editar fabricante.");
				});
		},
		salvarEdicaoFabricante: function () {
			const vm = this;
			axios.put("/mercado/rest/fabricantes/" + this.fabricante.id, this.fabricante)
				.then(function (response) {
					alert("Fabricante editado com sucesso.");
				})
				.catch(function (error) {
					alert("Erro ao editar fabricante.");
				})
				.finally(function () {
					vm.fabricante = {};
					vm.buscaFabricantes();
					vm.formPreenchido = false
				})
		},
		limparProduto: function () {
			this.produto = {}
			this.formPreenchido = false
		},
		limparFabricante: function () {
			this.fabricante = {}
			this.formPreenchido = false
		}

    }
});