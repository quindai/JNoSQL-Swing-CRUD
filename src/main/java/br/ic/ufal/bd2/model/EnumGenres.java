package br.ic.ufal.bd2.model;

public enum EnumGenres {
		A_VISTA, DEBITO, SEM_JUROS_2X, SEM_JUROS_3X;
		
		private int value;
		private EnumGenres() {
			value = ordinal();
		}
		
}
