import * as React from 'react';
import { createRoot } from 'react-dom/client';
import { AnotherComponent } from './exercises/daw-2122-tee-comp';
//import { Counter } from './exercises/counter-escolhamultipla';
//import { PeriodicFetcher } from './exercises/daw-2223-t1-comp';
//import { MyComponent } from './exercises/daw-2233-t2-comp';
//import { delayedRandomString } from './auxiliary/delayedRandomString';
//import { CounterComponent } from './exercises/useCounter';


const urls = [
    'https://httpbin.org/get',
    'https://httpbin.org/delay/2',
    'https://httpbin.org/delay/5',
  ];

const root = createRoot(document.getElementById('main-div'));
root.render(<AnotherComponent urls={urls}/>)
// root.render(<PeriodicFetcher uri = {"https://httpbin.org/get"} time = {1000}/>); // -> ex do teste 1 de 22/23

//root.render(<MyComponent f={delayedRandomString}/>) // -> ex do teste 2 de 22/23

//root.render(<CounterComponent/>) // -> ex do teste 1 de 22/23  

//root.render(<Counter/>)