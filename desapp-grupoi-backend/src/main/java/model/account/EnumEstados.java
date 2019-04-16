package model.account;

public class EnumEstados {

    public enum EstadoCredito {
        GUARDADO, ENCURSO, FINALIZADO;
    }

    public enum EstadoSituacionDeuda {
        NORMAL(){
            @Override
            public boolean esNormal() {
                return true;
            }
        },
        CUMPLIDOR(){
            @Override
            public boolean esCumplidor() {
                return true;
            }
        },
        MOROSO(){
            @Override
            public boolean esMoroso() {
                return true;
            }
        };


        public boolean esNormal(){
            return false;
        };
        public boolean esCumplidor(){
            return false;
        };
        public boolean esMoroso(){
            return false;
        };

    }
}
