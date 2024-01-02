import React from 'react';
import { useState, useEffect, useCallback } from 'react';

type Props = {
    f : () => Promise<string>
}

export function MyComponent( { f } : Props) {
    const [result, setResult] = useState("")
    const [error, setError] = useState("")
    const [pending, setPending] = useState(false)
    const [timer, setTimer] = useState(0)

    const fetcher = useCallback( async() => {
        try{
            const response = await f()
            setResult(response)
        }catch(error) {
            setError(error.message)
        }
        finally {
            setPending(false)
        }
    }, [f])

    useEffect( () => { 

        setPending(true)
        fetcher()
        
    }, [fetcher])



    useEffect( () => {
        if(pending) {
            const id = setInterval( () => setTimer(t => t + 1) , 100)
            return () => clearInterval(id)
        }
    }, [pending])
    
    if(pending) {
        return(
            <div>
                <p>Loading...</p>
                <p>Number of iterations : {timer} </p>
            </div>
        )
    }

    else if(!pending && result) {
        return(
            <div>
                <p>Result : {result} </p>
            </div>
        )
    }

    else if(!pending && error) {
        return(
            <div>
                <p> Error : {error} </p>
            </div>
        )
    }
    else return(<div> <p> mounting..</p></div>)
}