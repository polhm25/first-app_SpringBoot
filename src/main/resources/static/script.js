//Funcion para cargar los enemigos de la base de datos
document.addEventListener('DOMContentLoaded', function () {
    cargarEnemigos();

    const form = document.querySelector('.formInsertarEnemigos');
    if (form) {
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            guardarEnemigo();
        });
    }

    const btnCancelar = document.getElementById('btnCancelar');
    if (btnCancelar) {
        btnCancelar.addEventListener('click', function () {
            cancelarEdicion();
        });
    }
});

function cargarEnemigos() {
    fetch('/api/enemigo')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener los datos');
            }
            return response.json();
        })
        .then(datos => {
            const tableBody = document.getElementById('enemigosTableBody');
            tableBody.innerHTML = '';

            if (datos.length === 0) {
                tableBody.innerHTML = '<tr><td colspan="6" style="text-align: center; padding: 2rem;">No hay enemigos registrados</td></tr>';
                return;
            }

            datos.forEach(enemigo => {
                const fila = document.createElement('tr');

                fila.innerHTML = `
                    <td>${enemigo.id}</td>
                    <td>${enemigo.nombre}</td>
                    <td>${enemigo.pais}</td>
                    <td>${enemigo.afiliacion}</td>
                    <td>
                        <button class="btn-editar" onclick="editarEnemigo(${enemigo.id})">Editar</button>
                        <button class="btn-eliminar" onclick="eliminarEnemigo(${enemigo.id})">Eliminar</button>
                    </td>`;

                tableBody.appendChild(fila);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            const tableBody = document.getElementById('enemigosTableBody');
            tableBody.innerHTML = '<tr><td colspan="6" style="text-align: center; padding: 2rem; color: red;">Error al cargar los datos</td></tr>';
        });
}
//Funcion para guardar un enemigo (crear o editar)
function guardarEnemigo() {
    const enemigoId = document.getElementById('enemigoId').value;
    const nombre = document.getElementById('nombre').value;
    const genero = document.getElementById('genero').value;
    const pais = document.getElementById('paisOrigen').value;
    const afiliacion = document.getElementById('afiliacion').value;
    const activo = document.getElementById('activo').checked;

    const enemigo = {
        nombre: nombre,
        genero: genero,
        pais: pais,
        afiliacion: afiliacion,
        activo: activo
    };

    const url = enemigoId ? `/api/enemigo/${enemigoId}` : '/api/enemigo';
    const method = enemigoId ? 'PUT' : 'POST';

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(enemigo)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al guardar el enemigo');
            }
            return response.json();
        })
        .then(data => {
            console.log('Enemigo guardado:', data);
            cargarEnemigos();
            document.querySelector('.formInsertarEnemigos').reset();
            document.getElementById('enemigoId').value = '';
            document.getElementById('formTitle').textContent = 'Agregar terrorista';
            document.getElementById('btnSubmit').textContent = 'Agregar';
            document.getElementById('btnCancelar').style.display = 'none';
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al guardar el enemigo');
        });
}

//Funcion para editar un enemigo
function editarEnemigo(id) {
    fetch(`/api/enemigo/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener el enemigo');
            }
            return response.json();
        })
        .then(enemigo => {
            document.getElementById('enemigoId').value = enemigo.id;
            document.getElementById('nombre').value = enemigo.nombre;
            document.getElementById('genero').value = enemigo.genero || '';
            document.getElementById('paisOrigen').value = enemigo.pais;
            document.getElementById('afiliacion').value = enemigo.afiliacion;
            document.getElementById('activo').checked = enemigo.activo || false;

            document.getElementById('formTitle').textContent = 'Editar terrorista';
            document.getElementById('btnSubmit').textContent = 'Actualizar';
            document.getElementById('btnCancelar').style.display = 'inline-block';

            window.scrollTo(0, document.querySelector('.formularioContainer').offsetTop);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al cargar el enemigo');
        });
}

//Funcion para eliminar un enemigo
function eliminarEnemigo(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este enemigo?')) {
        fetch(`/api/enemigo/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al eliminar el enemigo');
                }
                cargarEnemigos();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error al eliminar el enemigo');
            });
    }
}

//Funcion para cancelar la edicion
function cancelarEdicion() {
    document.getElementById('enemigoId').value = '';
    document.querySelector('.formInsertarEnemigos').reset();
    document.getElementById('formTitle').textContent = 'Agregar terrorista';
    document.getElementById('btnSubmit').textContent = 'Agregar';
    document.getElementById('btnCancelar').style.display = 'none';
}