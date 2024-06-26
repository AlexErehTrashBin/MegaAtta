package trees;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class SimpleTree<T> {

    protected TreeItem root = null;
    protected int size = 0;
    protected Function<String, T> fromStrFunc;
    protected Function<T, String> toStrFunc;
    public SimpleTree(Function<String, T> fromStrFunc, Function<T, String> toStrFunc) {
        this.fromStrFunc = fromStrFunc;
        this.toStrFunc = toStrFunc;
    }

    public SimpleTree(Function<String, T> fromStrFunc) {
        this(fromStrFunc, Object::toString);
    }

    public SimpleTree() {
        this(null);
    }

    public void clear() {
        root = null;
    }

    private T fromStr(String s) throws Exception {
        s = s.trim();
        if (s.length() > 0 && s.charAt(0) == '"') {
            s = s.substring(1);
        }
        if (s.length() > 0 && s.charAt(s.length() - 1) == '"') {
            s = s.substring(0, s.length() - 1);
        }
        if (fromStrFunc == null) {
            throw new Exception("Не определена функция конвертации строки в T");
        }
        return fromStrFunc.apply(s);
    }

    private void skipSpaces(String bracketStr, IndexWrapper iw) {
        while (iw.index < bracketStr.length() && Character.isWhitespace(bracketStr.charAt(iw.index))) {
            iw.index++;
        }
    }

    private T readValue(String bracketStr, IndexWrapper iw) throws Exception {
        // пропуcкаем возможные пробелы
        skipSpaces(bracketStr, iw);
        if (iw.index >= bracketStr.length()) {
            return null;
        }
        int from = iw.index;
        boolean quote = bracketStr.charAt(iw.index) == '"';
        if (quote) {
            iw.index++;
        }
        while (iw.index < bracketStr.length() && (
                quote && bracketStr.charAt(iw.index) != '"' ||
                        !quote && !Character.isWhitespace(bracketStr.charAt(iw.index)) && "(),".indexOf(bracketStr.charAt(iw.index)) < 0
        )) {
            iw.index++;
        }
        if (quote && bracketStr.charAt(iw.index) == '"') {
            iw.index++;
        }
        String valueStr = bracketStr.substring(from, iw.index);
        T value = fromStr(valueStr);
        skipSpaces(bracketStr, iw);
        return value;
    }

    private TreeItem fromBracketStr(String bracketStr, IndexWrapper iw) throws Exception {
        T parentValue = readValue(bracketStr, iw);
        TreeItem parentNode = new TreeItem(parentValue);
        size++;
        if (bracketStr.charAt(iw.index) == '(') {
            iw.index++;
            skipSpaces(bracketStr, iw);
            if (bracketStr.charAt(iw.index) != ',') {
                parentNode.left = fromBracketStr(bracketStr, iw);
                size++;
                skipSpaces(bracketStr, iw);
            }
            if (bracketStr.charAt(iw.index) == ',') {
                iw.index++;
                skipSpaces(bracketStr, iw);
            }
            if (bracketStr.charAt(iw.index) != ')') {
                parentNode.right = fromBracketStr(bracketStr, iw);
                size++;
                skipSpaces(bracketStr, iw);
            }
            if (bracketStr.charAt(iw.index) != ')') {
                throw new Exception(String.format("Ожидалось ')' [%d]", iw.index));
            }
            iw.index++;
        }

        return parentNode;
    }

    public void fromBracketNotation(String bracketStr) throws Exception {
        IndexWrapper iw = new IndexWrapper();
        TreeItem root = fromBracketStr(bracketStr, iw);
        if (iw.index < bracketStr.length()) {
            throw new Exception(String.format("Ожидался конец строки [%d]", iw.index));
        }
        this.root = root;
    }

    private boolean checkRecursive(TreeItem item, Set<T> valuesSet) {
        if (item == null) return false;
        if (valuesSet.contains(item.value)) return true;
        valuesSet.add(item.value);
        boolean containsLeft = checkRecursive(item.left, valuesSet);
        boolean containsRight = checkRecursive(item.right, valuesSet);
        return containsLeft || containsRight;
    }

    public boolean checkForDuplicatesTask() {
        Set<T> valuesSet = new HashSet<>();
        valuesSet.add(root.value);
        boolean containsLeft = checkRecursive(root.left, valuesSet);
        boolean containsRight = checkRecursive(root.right, valuesSet);
        return containsLeft || containsRight;
    }

    private static class IndexWrapper {
        public int index = 0;
    }

    protected class TreeItem {
        public T value;
        public TreeItem left;
        public TreeItem right;

        public TreeItem(T value, TreeItem left, TreeItem right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public TreeItem(T value) {
            this(value, null, null);
        }

    }
}
