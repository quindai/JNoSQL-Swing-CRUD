package br.ic.ufal.bd2.model;

public enum EnumGenres {
		MASCULINO, FEMININO, OUTRO;
		
		private int value;
		private EnumGenres() {
			value = ordinal();
		}
		
}
