/**
 * 
 */
var urlPage = location.pathname;
var Actividad = null;
var urlResource = "/api/production/actividad";

document.addEventListener("DOMContentLoaded", function () {
	
	Actividad = new Vue({
		el: "#activity",
		data: {
			id: 0,
			descripcion: ""
		},
		methods: {
			setData: function(data) {
				this.id = data.id;
				this.descripcion = data.descripcion;
			},
			getData: function() {
				return JSON.parse(JSON.stringify(this.$data));
			}
		},
		beforeMount: function() {
			if(getUrlValue().id != undefined){
				let url = urlResource + "/" + getUrlValue().id; 
				getData(url).onload = function(data){
					var response = JSON.parse(this.responseText);
					Actividad.setData(response);
				};
			}
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		let object = Actividad.getData();
		let url = urlResource;
		postData(url, object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
            		console.log(response);
            		Actividad.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});