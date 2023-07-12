public class Venta {
    private Producto producto;
    private String medioPago;

    private int cuotas;

    public Venta(Producto producto, String medioPago) {
        this.producto = producto;
        this.medioPago = medioPago;
    }

    //Constructor para tarjeta de credito
    public Venta(Producto producto, String medioPago, int cuotas) {
        this.producto = producto;
        this.medioPago = medioPago;
        //solo se permite ingresar cienta cantidad de cuotas
        if (cuotas == 2 || cuotas == 3 || cuotas ==6){
            this.cuotas = cuotas;
        }else{
            throw new IllegalArgumentException("Las cantidad de cuotas ingresadas no son validas");
        }
    }


    public void procesarVenta() {
        if (producto.getCantidadEnStock() > 0) {
            double montoTotal = producto.getPrecioUnitario();

            if (medioPago.toUpperCase().equals("DEBITO")) {
                // No hay descuento, se cobra el valor total de la venta
            } else if (medioPago.toUpperCase().equals("EFECTIVO")) {
                //se resta el 10%, por utilizar efectivo
                montoTotal -= (montoTotal * 0.1);
            } else if (medioPago.toUpperCase().equals("CREDITO")) {
                if (cuotas == 2) {
                    montoTotal += (montoTotal * 0.06);
                } else if (cuotas == 3) {
                    montoTotal += (montoTotal * 0.12);
                } else if (cuotas == 6) {
                    montoTotal += (montoTotal * 0.20);
                }
            }

            producto.setCantidadEnStock(producto.getCantidadEnStock() - 1);
            if (producto.getStockMinimo() == producto.getCantidadEnStock()){
                System.out.println();
            }
            // Realizar el registro de la venta y otros procesamientos necesarios
            System.out.println("Venta procesada exitosamente.");
            System.out.println("Monto total: " + montoTotal);
        } else {
            System.out.println("No hay stock suficiente del producto.");
        }
    }
}
