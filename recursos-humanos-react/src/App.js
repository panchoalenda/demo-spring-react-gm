import Navegacion from "./plantilla//Navegacion"
import ListadoEmpleados from "./empleados/ListadoEmpleados";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import AgregrEmpleado from "./empleados/AgregrEmpleado";
import EditarEmpleado from "./empleados/EditarEmpleado";

function App() {
  return (
    <div className="container">
      <BrowserRouter>
        <Navegacion/>
          <Routes>
            <Route exact path="/" element={<ListadoEmpleados />} />
            <Route exact path="/agregar" element={<AgregrEmpleado />} />
            <Route exact path="/editar/:id" element={<EditarEmpleado />} />
          </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
