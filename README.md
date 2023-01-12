# Scientific Calculator

[![Test Cases](https://github.com/cybercoder-naj/ScientificCalculator/actions/workflows/testApp.yaml/badge.svg?event=push)](https://github.com/cybercoder-naj/ScientificCalculator/actions/workflows/testApp.yaml)

Solves mathematical equations from a string. 
This is an improvement to my previous repository, [ComplexCalculator](https://github.com/cybercoder-naj/ComplexCalculator.git)

## Installation

*This repository has not been hosted yet*'

## Milestones

 - [x] Implement a Lexer.
 - [x] Implement a Parser.
 - [ ] Solve equations without variables.
   - [x] Addition.
   - [x] Subtraction.
   - [x] Multiplication.
   - [x] Division.
   - [x] Unary Plus.
   - [x] Unary Minus.
   - [x] Order with Bracketing.
   - [ ] Trigonometric functions.
   - [ ] Logarithm function.
   - [ ] Absolute function.
 - [ ] Solve equations with variables.
 - [ ] Differentiate equations.
 
## Usage

```kotlin
fun main() {
   val answer = ScientificCalculator.solve("39+5/3*(10 - 8)")
   println(answer) // prints 42.333333333333336
}
```

## Authors

 - [Nishant Aanjaney Jalan](https://github.com/cybercoder-naj) 