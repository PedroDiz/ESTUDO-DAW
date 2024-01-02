import React from 'react';
import { useState, useEffect, useCallback } from 'react';

type FetcherProps = {
    time : number;
    uri : string;
}

export function PeriodicFetcher( { time, uri} : FetcherProps ) {
    const [content, setContent] = useState("")
    const [error, setError] = useState("")

    const fetcher = useCallback(async () => {
        try {
          const response = await fetch(uri);
          const data = await response.text();
          setContent(data);
          setError(null);
        } catch (error) {
          setError(error.message)
          setContent(null)
        }
      }, [uri]);

    useEffect( () => {
        const id = setInterval(fetcher, time)
        return () => { clearInterval(id)}
    }, [time, fetcher])

    return(
        <div>
            <textarea value={error == null ? content : error} rows={80} cols={80}></textarea>
        </div>
    )

}