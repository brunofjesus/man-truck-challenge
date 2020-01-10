import { PluckPipe } from "./pluck.pipe";

describe('PluckPipe', () => {
  let pluckPipe: PluckPipe;

  // synchronous beforeEach
  beforeEach(() => {
    pluckPipe = new PluckPipe();
  });

  it('should be instanciated', () => {
    expect(pluckPipe).toBeDefined();
  });

  it('should return empty array if no input given', () => {
    const input = null;
    const key = 'test';

    const pluck = pluckPipe.transform(input, key);

    expect(pluck).toEqual([]);
  });

  it('should return empty array if no key given', () => {
    const input = [];
    const key = null;

    input["test"] = 20;

    const pluck = pluckPipe.transform(input, key);

    expect(pluck).toEqual([]);
  });

  it('should get the key', () => {
    const input = [{ "test": 20 }];
    const key = "test";

    const pluck = pluckPipe.transform(input, key);

    expect(pluck).toEqual([20]);
  });

  it('should be null, invalid key', () => {
    const input = [];
    const key = "invalid";

    input["test"] = 20;

    const pluck = pluckPipe.transform(input, key);

    expect(pluck).toEqual([]);
  });
});

