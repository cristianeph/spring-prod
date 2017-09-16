/**
 * 
 */

var urlPage = location.pathname;
var urlResource = "/api/production/cliente";
var urlForm = "/production/cliente/form";
var list;
var listData;

document.addEventListener("DOMContentLoaded", function(){
	list = new Vue({
		el: '#list',
		data: {
			customers: listData
		},
		methods: {
			getEditionForm: function(customer){
				return urlForm + "?id=" + customer.id;
			}
		},
		beforeMount: function(){
			let that = this;
			let url = urlResource + "?page=1&size=15"
			getData(url).onload = function(){
				var response = JSON.parse(this.responseText);
				console.log("valor antes de carga", that.customers);
				that.customers = response.content;
				console.log("valor despues de carga", that.customers);
			}
		}
	})
});