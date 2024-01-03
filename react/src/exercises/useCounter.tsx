import React from 'react';
import { useState} from 'react';

function useCounter(initialValue : number) : [observed: number, inc: () => void, dec: () => void] {
    const [value, setValue] = useState(initialValue)

    const inc = () => { setValue(x => x + 1)}
    const dec = () => { setValue(x => x - 1)}

    return [value, inc, dec]
}

export function CounterComponent() {
    const [count, increment, decrement] = useCounter(0)
  
    return (
      <div>
        <p>Counter Value: {count}</p>
        <button onClick={increment}>Increment</button>
        <button onClick={decrement}>Decrement</button>
      </div>
    )
  }
  