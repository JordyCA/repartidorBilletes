# <strong>Api Rest </strong> - Cajero

La finalidad es poder realizar un retiro de efectivo y que el cajero determine el dinero que entrego al cliente junto con la cantidad y denominación.

## Funcionalidades 
- [x] Descuento del dinero existente.
- [x] Retorna la catidad y denominación del dinero entregado al cliente final
- [x] Se guarda el historico de transacciones realizado
- [x] Se almacena la información en una tabla en memoria

## Peticiones

| Ruta | Tipo | Descripción |
| --- | --- | --- |
| http://localhost:9091/cajero/v1/admin/listaDenominacion | Get | Muestra todo el efectivo que tiene el cajero |
| http://localhost:9091/cajero/v1/admin/retiroEfectivo | Post | Realiaza el retiro del cajero |

<br>

>[!important]
> Los datos son almacenados en memoria si se detiene el proyecto se pierde la información

## Otros

Consola de h2 - http://localhost:9091/cajero/v1/h2-console

