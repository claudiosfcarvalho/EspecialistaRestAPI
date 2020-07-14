package com.claudiowork.algafood.di.notificacao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RetentionPolicy.RUNTIME) //especifica o tempo que a notificação deve permanecer aonde foi usada
@Qualifier
public @interface TipoDoNotificador {

	NivelUrgencia value();
}
