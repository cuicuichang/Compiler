package syntaxtree;
import java.util.List;

class LoxFunction implements LoxCallable {
 	private final Stmt.Function declaration;
  	LoxFunction(Stmt.Function declaration) {
    	this.declaration = declaration;
  	}

  	@Override
  	public String toString() {
    	return "<fn " + declaration.name.lexeme + ">";
  	}

  	@Override
  	public int arity() {
    	return declaration.parameters.size();
  	}

	@Override
  	public Object call(Interpreter interpreter, List<Object> arguments) {
    	Environment environment = new Environment(interpreter.globals);
    	for (int i = 0; i < declaration.parameters.size(); i++) {
      	environment.define(declaration.parameters.get(i).lexeme,
        arguments.get(i));
    	}

    try {
    	interpreter.executeBlock(declaration.body, environment);
    } catch (Return returnValue) {
      return returnValue.value;
    }
    	return null;
  	}
}