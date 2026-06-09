localStorage.setItem('jwtToken', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc4MDk3MDYxMCwiZXhwIjoxNzgxMDU3MDEwfQ.DGt5x5Ub9BPZlVdsLqDg--ue2cQye076lUPe9c04kg4');
const API_URL = '/api/pacientes';

// Función auxiliar para obtener el token guardado en el navegador
function getToken() {
    return localStorage.getItem('jwtToken');
}

document.addEventListener('DOMContentLoaded', cargarPacientes);

document.getElementById('pacienteForm').addEventListener('submit', function(e){
	e.preventDefault();
	crearPaciente();
});

function cargarPacientes(){
    const token = getToken();
    
	fetch(API_URL, {
        method: 'GET',
        headers: {
            'Authorization': token ? `Bearer ${token}` : '' // ¡AQUÍ ENVIAMOS LA PULSERA!
        }
    })
	.then(response => {
        if (response.status === 403) {
            console.error("Acceso denegado: No tienes un token válido.");
            return []; // Retorna un arreglo vacío si el portero nos bloquea
        }
        return response.json();
    })
	.then(data => {
		const tbody = document.querySelector('#pacientesTable tbody');
		tbody.innerHTML = '';
		
		data.forEach(paciente => {
			const tr = document.createElement('tr');
			tr.innerHTML = `
			<td>${paciente.id}</td>
			<td>${paciente.nombre}</td>
			<td>${paciente.edad}</td>
			<td>${paciente.correo}</td>
			<td>${paciente.telefono}</td>
			<td>
				<button class="btn-delete" onclick="eliminarPaciente(${paciente.id})">Eliminar</button>
			</td>
			`;
			tbody.appendChild(tr);
		});
	})
	.catch(error => console.error("Error al cargar paciente:", error));
}

function crearPaciente(){
    const token = getToken();
	const nuevoPaciente ={
		nombre : document.getElementById('nombre').value,
		edad : parseInt(document.getElementById('edad').value),
		correo : document.getElementById('correo').value,
		telefono : document.getElementById('telefono').value
	};
	
	fetch(API_URL,{
		method : 'POST',
		headers : {
			'Content-Type' : 'application/json',
            'Authorization': token ? `Bearer ${token}` : '' // ¡AQUÍ ENVIAMOS LA PULSERA!
		},
		body: JSON.stringify(nuevoPaciente)	
	})
	.then(response => {
		if(response.ok){
			cargarPacientes();
			document.getElementById('pacienteForm').reset();
		} else {
			alert("Error al guardar. Verifica tus datos o tu sesión.");
		}
	})
	.catch(error => console.error("Error al crear paciente", error));
}

function eliminarPaciente(id){
    const token = getToken();
	if(confirm("¿Estas seguro de eliminar este paciente?")){
		fetch(`${API_URL}/${id}`, { 
			method : 'DELETE',
            headers: {
                'Authorization': token ? `Bearer ${token}` : '' // ¡AQUÍ ENVIAMOS LA PULSERA!
            }
		})
		.then(response => {
			if(response.ok){
				cargarPacientes();
			} else {
                alert("No tienes permisos para eliminar.");
            }
		})
		.catch(error => console.error("Error al eliminar:", error));
	}	
}