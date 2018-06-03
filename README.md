# README

## Start repl with boot

With rebel readline (better repl)
```
make repl
```

## Cheatsheet

Load repl utils inside the repl:
`(use 'clojure.repl)`

Compiling on the repl:
```
=> (set-env!
    :resource-paths #{"src"})
=> (require '[advent.core])
```
