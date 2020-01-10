import { JoinPipe } from "./join.pipe";

describe('JoinPipe', () => {
  let joinPipe: JoinPipe;

  // synchronous beforeEach
  beforeEach(() => {
    joinPipe = new JoinPipe();
  });

  it('should be instanciated', () => {
    expect(joinPipe).toBeDefined();
  });

  it('should return empty string if no items given', () => {
    const items = null;

    const joined = joinPipe.transform(items, '');

    expect(joined).toEqual('');
  });

  it('should join without separator (null)', () => {
    const items = ["MAN","Truck"];

    const joined = joinPipe.transform(items, null);

    expect(joined).toEqual("MANTruck");
  });

  it('should join without separator (undefined)', () => {
    const items = ["MAN","Truck"];

    const joined = joinPipe.transform(items, undefined);

    expect(joined).toEqual("MANTruck");
  });

  it('should join with specified separator (space)', () => {
    const items = ["MAN","Truck"];

    const joined = joinPipe.transform(items, ' ');

    expect(joined).toEqual("MAN Truck");
  });

  it('should join with specified separator (space and comma)', () => {
    const items = ["MAN","Truck"];

    const joined = joinPipe.transform(items, ', ');

    expect(joined).toEqual("MAN, Truck");
  });
});

