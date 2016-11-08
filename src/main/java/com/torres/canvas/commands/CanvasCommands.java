package com.torres.canvas.commands;

import com.torres.canvas.commands.Exception.CommandException;
import com.torres.canvas.model.Canvas;
import com.torres.canvas.model.CanvasConsumer;
import com.torres.canvas.model.Line;
import com.torres.canvas.model.Rectangle;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

import static com.torres.canvas.commands.Exception.CommandException.Type.INVALID_ARGUMENTS;
import static com.torres.canvas.commands.Exception.CommandException.Type.REQUIRED;

/**
 * @author Javier Torres
 */
@Component
public class CanvasCommands implements CommandMarker {

	private Optional<Canvas> canvas = Optional.empty();
	private static final String PARAM_SEPARATOR = " ";

	@CliCommand(value = "C", help = "Creates a new canvas")
	public void createCanvas(
			@CliOption(key = { "" }, mandatory = true, help = "Type the width and height") final String params) {

		try {
			boolean valid = validate(v -> (v.matches("\\d+ \\d+")), params);
			if (!valid) {
				return;
			}

			String[] p = params.split(PARAM_SEPARATOR);
			canvas = Optional.of(new Canvas(Integer.valueOf(p[0]), Integer.valueOf(p[1])));
			canvas.get().print();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@CliCommand(value = "L", help = "Creates a new line")
	public void createLine(
			@CliOption(key = { "" }, mandatory = true, help = "Type the x0 x1 y0 y1") final String params) {

		boolean valid = validate(v -> (v.matches("\\d+ \\d+ \\d+ \\d+")), params);
		if (!valid) {
			return;
		}

		String[] p = params.split(PARAM_SEPARATOR);
		execute(canvas -> canvas.addGeometry(new Line(
				Integer.valueOf(p[0]),
				Integer.valueOf(p[1]),
				Integer.valueOf(p[2]),
				Integer.valueOf(p[3]))));
	}

	@CliCommand(value = "R", help = "Creates a new rectangle")
	public void createRectangle(
			@CliOption(key = { "" }, mandatory = true, help = "Type the x0 x1 y0 y1") final String params) {

		boolean valid = validate(v -> (v.matches("\\d+ \\d+ \\d+ \\d+")), params);
		if (!valid) {
			return;
		}

		String[] p = params.split(PARAM_SEPARATOR);
		execute(canvas -> canvas.addGeometry(new Rectangle(
				Integer.valueOf(p[0]),
				Integer.valueOf(p[1]),
				Integer.valueOf(p[2]),
				Integer.valueOf(p[3]))));
	}

	@CliCommand(value = "B", help = "Fills the entire area connected to x,y")
	public void fillArea(
			@CliOption(key = { "" }, mandatory = true, help = "Type the x y c") final String params) {

		boolean valid = validate(v -> (v.matches("\\d+ \\d+ .")), params);
		if (!valid) {
			return;
		}

		String[] p = params.split(PARAM_SEPARATOR);
		execute(canvas -> canvas.addFilling(
				Integer.valueOf(p[0]),
				Integer.valueOf(p[1]),
				p[2].charAt(0)));
	}

	/**
	 * Executes a CanvasConsumer. If there is an error, print ous on the System.err.
	 *
	 * @param canvasConsumer
	 */
	private void execute(CanvasConsumer canvasConsumer) {
		try {
			if (!canvas.isPresent()) {
				throw new CommandException(REQUIRED.getMessage());
			}

			canvasConsumer.accept(canvas.get());
			canvas.get().print();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Validate the argument contains in p with the Predicate.
	 *
	 * @param predicate
	 * @param p
	 * @return
	 */
	public boolean validate(Predicate<String> predicate, String p) {
		if (!predicate.test(p)) {
			System.err.println(INVALID_ARGUMENTS.getMessage());
			return false;
		}

		return true;
	}
}
