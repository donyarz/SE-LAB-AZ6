package semantic.symbol;

public enum SymbolType {
    Int(codeGenerator.varType.Int),
    Bool(codeGenerator.varType.Bool);

    public final codeGenerator.varType varType;

    SymbolType(codeGenerator.varType varType) {
        this.varType = varType;
    }
}
