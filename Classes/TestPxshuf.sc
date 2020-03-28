TestPxshuf : UnitTest {
    test_yieldOnce {
        var pattern = Pxshuf([0], 1).asStream;
        var result = pattern.nextN(2);
        this.assert(result.size == 2, "Size is 2");
        this.assert(result[0].isNumber, "First item is a number");
        this.assert(result[1].isNil, "Second item is nil");
    }

    test_yieldTwice {
        var pattern = Pxshuf([0,1], 1).asStream;
        var result = pattern.nextN(3);
        this.assert(result.size == 3, "Size is 3");
        this.assert(result[0].isNumber, "First item is a number");
        this.assert(result[1].isNumber, "Second item is a number");
        this.assert(result[2].isNil, "Third item is nil");
    }

    test_yieldUnique {
        var pattern = Pxshuf([\one,\two,\three,\four], inf).asStream;
        var result = pattern.nextN(104).clump(4);
        var tmp;
        result.size.do {|i|
            var item = result[i];
            if (tmp.notNil) {
                this.assert(item.first != tmp.last, "(%) != (%)".format(tmp.last, item.first));
            };
            tmp = item;
        };
    }
}
