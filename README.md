# Pxshuf

Sequentially embed values from a list in a random order with no repetitions

## Example

```supercollider
x = Pxshuf([0, 1, 2, 3], 3).asStream;
x.nextN(13).clump(4); // [ [ 1, 0, 2, 3 ], [ 2, 1, 0, 3 ], [ 2, 3, 1, 0 ], [ nil ] ]
```

## Unit test

```supercollider
TestPxshuf.run;
```
