import React from 'react';
import { useState, useEffect } from 'react';

type Props = {
    urls : string[]
}

type Item = {
    url : string,
    text : string
}

export function AnotherComponent( {urls} : Props) {
    const [items, setItems] = useState<Item[]>([])


    useEffect(() => {
        const fetchData = async () => {
            for (const url of urls) {
                try {
                    const response = await fetch(url);
                    const text = await response.text();
                    setItems((prevItems) => [...prevItems, { url, text }]);
                } catch (error) {
                    setItems((prevItems) => [...prevItems, { url, text: error.message }]);
                }
            }
        }    
        fetchData();
      }, [urls]);

      return (
        <div>
          <h1>Items:</h1>
          <ul>
            {items.map((item, index) => (
              <li key={index}>{`${item.url}-------------->`} {item.text}</li>
            ))}
          </ul>
        </div>
      );
    
}
