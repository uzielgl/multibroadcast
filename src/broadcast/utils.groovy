/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package broadcast
import java.util.*;

/**
 *
 * @author uzielgl
 */

/** Define si pasa la condición causal
 * @param vt [1,0,1,2]
 * @param hm [ [2, 3], [2,4] ]
 * @return boolean  - Define si pasa o no la condición
 **/
def isCausal( ArrayList vt, ArrayList hm ){
    Iterator it =  hm.iterator();
    while( it.hasNext() ){
        def t = it.next();
        def l = t[0];
        def tl = t[1];
        if( ! ( tl <= vt[l] ) )
            return false;
    }
    return true;
}


print isCausal( [0,0,0,0] , [[1, 1], [0, 1]]);


def lista = [];

lista.add( [1, 2] );
lista.add( [1, 2] )


/** Elimina todas las tuplas que k=s del ci
 **/
def deleteKS( int k, ArrayList ci ){
    Iterator it = ci.iterator();
    def new_ci = [];
    while( it.hasNext() ){
        def t = it.next();
        if( !( t[0] == k ) )
            new_ci.add( t );
    }
    return new_ci;
}

/** Elimina todas las tuplas de Hm que están en Ci
 * Nota: Regresa tuplas repetidas
 **/
def deleteHmCi(Hm, Ci){
    Iterator it_ci = Ci.iterator();
    def new_ci = [];
    while( it_ci.hasNext() ) {
        def t_ci = it_ci.next();
        Iterator it_hm = Hm.iterator();
        def add = true;
        while( it_hm.hasNext() ) {
            def t_hm = it_hm.next();
            if(  t_ci[0] == t_hm[0] && t_ci[1] == t_hm[1]  ){
                add = false;
                break;
            }
        }
        if (add == true){
           new_ci.add( t_ci );
        }
    }
    return new_ci;
}

//print deleteHmCi( [ [2,1], [3,1] ], [ [1,1], [4,1], [2,1], [3,1], [2,1], [3,1], [1,1] ]  );

//print deleteKS( 5, [ [1,3], [2, 2], [1,4] ] );
//print deleteKS( 5, [ [1,3], [2, 2], [1,4] ] );


import com.google.gson.Gson;

Gson gson = new Gson();

//print gson.toJson( [1, 1, 1, []] );

//print gson.fromJson( gson.toJson( [1, 1, 1, []] ), ArrayList.class );

//print 3.0 == 3;


cola_mensajes = [];
 def addColaMensaje( message ){
        Iterator it = cola_mensajes.iterator();
        def add = true;
        while( it.hasNext() ){
            def msg = it.next();
            if( msg[0] == message[0] && msg[1] == message[1] ){
                add = false;
                break;
            }
        }
        if( add == true) 
            cola_mensajes.add( message );
    }
    
 def m1 = [1.0, 1.0, , [[0.0, 1.0]]];
 def m2 = [1, 1, , [[0.0, 1.0]]];
 
addColaMensaje( m1 );
addColaMensaje( m2 );

print cola_mensajes;