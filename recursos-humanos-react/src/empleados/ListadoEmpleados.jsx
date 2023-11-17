import axios from "axios";
import React, { useEffect, useState } from "react";
import { NumericFormat } from "react-number-format";
import { Link, useNavigate } from "react-router-dom";

export default function ListadoEmpleados() {
  let navegacion = useNavigate();

  const urlBase = "http://localhost:8080/rh-app/empleados";

  const [empleados, setEmpleados] = useState([]);

  useEffect(() => {
    cargarEmpleados();
  }, []);

  const cargarEmpleados = async () => {
    const resultado = await axios.get(urlBase); //Instalamos desde consola axios usando "npm i axios"
    console.log("Resultado Cargar empleados");
    console.log(resultado.data);
    setEmpleados(resultado.data);
  };

 const eliminarEmpleado = async (id) => {
    const urlBAse = "http://localhost:8080/rh-app/empleados/eliminar";
    await axios.delete (`${urlBase}/${id}`);
    //Una vez que terminamos redirigimos a la página de incio
    cargarEmpleados();
  };

  return (
    <div className="container">
      <div className="container text-center" style={{ margin: "30px" }}>
        <h3>Sistema de Recursos Humanos</h3>
      </div>
      <table className="table table-striped table-hover align-middle">
        <thead className="table-dark">
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Empleado</th>
            <th scope="col">Departamento</th>
            <th scope="col">Sueldo</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          {
            //Iteramos el arreglo de empleados
            empleados.map((empleado, indice) => (
              <tr key={indice}>
                <th scope="row">{empleado.id}</th>
                <td>{empleado.nombre}</td>
                <td>{empleado.departamento}</td>
                <td>
                  <NumericFormat
                    value={empleado.sueldo}
                    displayType={"text"}
                    thousandSeparator=","
                    prefix={"$"}
                    decimalScale={2}
                    fixedDecimalScale
                  />
                </td>
                <td>
                  <Link to={`/editar/${empleado.id}`} className="btn btn-warning btn-sm me-3"
                  >Editar</Link>
               
                  <button className="btn btn-danger btn-sm me-3" onClick={() => eliminarEmpleado(empleado.id)}>Eliminar</button>
                </td>
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
}
