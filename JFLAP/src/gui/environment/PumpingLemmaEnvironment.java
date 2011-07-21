/*
 *  JFLAP - Formal Languages and Automata Package
 * 
 * 
 *  Susan H. Rodger
 *  Computer Science Department
 *  Duke University
 *  August 27, 2009

 *  Copyright (c) 2002-2009
 *  All rights reserved.

 *  JFLAP is open source software. Please see the LICENSE for terms.
 *
 */





package gui.environment;

import gui.environment.tag.Tag;
import gui.pumping.PumpingLemmaChooser;
import gui.pumping.PumpingLemmaChooserPane;
import gui.pumping.RegPumpingLemmaChooser;

/**
 * An environment for {@link pumping.PumpingLemma}s. 
 * 
 * @author Jinghui Lim
 *
 */
public class PumpingLemmaEnvironment extends Environment 
{
    public PumpingLemmaEnvironment(PumpingLemmaChooser lemma) 
    {
        super(lemma);
    }

	public PumpingLemmaEnvironment(RegPumpingLemmaChooser object,
			PumpingLemmaChooserPane pumpingLemmaChooserPane, String string,
			Tag editorPermanentTag) {
		super(object, pumpingLemmaChooserPane, string, editorPermanentTag);
	}
}
