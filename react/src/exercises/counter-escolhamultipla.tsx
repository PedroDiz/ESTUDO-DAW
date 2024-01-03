import React from 'react';
import { useState, useEffect} from 'react';

export function Counter() {
    const [value, setValue] = useState(0)
    useEffect(() => {
        const tid = setInterval(() => setValue(value + 1), 1000)
        return () => {clearInterval(tid)}
    }, [])
    return (
        <div>{value}</div>
    )
    }