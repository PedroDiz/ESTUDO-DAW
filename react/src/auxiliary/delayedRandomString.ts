export function delayedRandomString(): Promise<string> {
    return new Promise((resolve) => {
      setTimeout(() => {
        const randomString = Math.random().toString(36).substring(7);
        resolve(randomString);
      }, 4000);
    })
  }