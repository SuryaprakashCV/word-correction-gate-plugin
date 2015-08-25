Word correction plugin will enable correction of misspelled words in GATE
# Introduction #
> This is a gate plugin, which helps to correct the misspelled words in English text.

# Problem Statement #

> Handling noisy text is one of the biggest problem facing social media analytics. for example:  for example: "too" can be "tooooo" or "tooooooo".
> We identified 2 problems here,
> How to analyze the social media comments that contains noisy texts?
> How to POS Tag those texts properly with out changing the actual text?

# How to solve #

> Here we are introducing a light weight method to solve this problem using Regular expressions.It helps to Identify misspelled words and POS tagger to process text without changing the actual text.

> There is a sample file(ExtendedList.txt) in the plugin root folder. It contains popular short hands usually present in user comments. After loading the plugin user has to point to the regex file. Users can create their own regex file or modify the existing one. format should be {regex}={word}

# How it works #

> It will go through all the tokens inside the text and find out matches for the regular expressions. It will change the value of Token.string to the actual word.

# Download #
> Word Correction is available here: https://code.google.com/p/word-correction-gate-plugin/downloads/list<br>
<blockquote>Check out source: svn checkout <a href='http://word-correction-gate-plugin.googlecode.com/svn/trunk/'>http://word-correction-gate-plugin.googlecode.com/svn/trunk/</a></blockquote>

<h1>How to use</h1>

<ol><li>Load plugin to gate <br>
</li></ol><blockquote>2. Select "WordCorrection" from processing resource menu <br>
3. Select the regex file <br>
4. Open pipeline<br>
<blockquote>Pipeline structure <br>
<blockquote>Tokenizer<br>
Sentence Splitter<br>
WordCorrection <br>
POS tagger<br>
</blockquote></blockquote>5. Run pipeline<br>
6. Check the Token.string value of misspelled word<br></blockquote>



Copyright (c) 2012, Moonraft Innovation Labs<br>
<blockquote>(<a href='http://www.moonraft.com'>http://www.moonraft.com</a>)