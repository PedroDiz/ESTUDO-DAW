import * as React from 'react';
import { createRoot } from 'react-dom/client';
//import { PeriodicFetcher } from './exercises/daw-2223-t1-comp';
//import { MyComponent } from './exercises/daw-2233-t2-comp';
//import { delayedRandomString } from './auxiliary/delayedRandomString';
import { CounterComponent } from './exercises/useCounter';


const root = createRoot(document.getElementById('main-div'));
// root.render(<PeriodicFetcher uri = {"https://httpbin.org/get"} time = {1000}/>); // -> ex do teste 1 de 22/23

//root.render(<MyComponent f={delayedRandomString}/>)

root.render(<CounterComponent/>)