/********************************************************************/
/* Archivo: MyListenerProducto.java                                 */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se escuchan los clics                 */
/********************************************************************/

package GUI;

import Dominio.Producto;

public interface MyListenerProducto {
    public void onClickListener(Producto p);
}
