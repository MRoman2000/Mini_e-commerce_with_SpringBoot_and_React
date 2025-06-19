import { useState } from 'react';

export default function Notificacion({ mensaje, mostrar }) {
  return (
    mostrar && (
      <div className="notificacion">
        {mensaje}
      </div>
    )
  );
}
