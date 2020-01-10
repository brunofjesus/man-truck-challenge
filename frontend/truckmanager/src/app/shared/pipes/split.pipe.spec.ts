import { SplitPipe } from "./split.pipe";

describe('SplitPipe', () => {
  let splitPipe: SplitPipe;

  // synchronous beforeEach
  beforeEach(() => {
    splitPipe = new SplitPipe();
  });

  it('should be instanciated', () => {
    expect(splitPipe).toBeDefined();
  });

  it('should return empty string if no input given', () => {
    const input = undefined;
    const separator = '.';
    const limit = undefined;

    const splited = splitPipe.transform(input, separator, limit);

    expect(splited).toEqual("");
  });

  it('should split with | if null separator given', () => {
    const input = "this|is|sparta";
    const separator = null;
    const limit = undefined;

    const splited = splitPipe.transform(input, separator, limit);

    expect(splited).toEqual(["this","is","sparta"]);
  });

  it('should obey limit', () => {
    const input = "this|is|sparta";
    const separator = null;
    const limit = 2;

    const splited = splitPipe.transform(input, separator, limit);

    expect(splited).toEqual(["this","is"]);
  });

  it('should use custom separator', () => {
    const input = "this,is,sparta";
    const separator = ",";
    const limit = undefined;

    const splited = splitPipe.transform(input, separator, limit);

    expect(splited).toEqual(["this","is","sparta"]);
  });
});

