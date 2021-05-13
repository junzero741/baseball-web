const getRandomPitchResult = () => {
  let result;
  const num = parseInt(Math.random() * 10); // 0~9
  if (num >= 0 && num <= 1) result = 'B';
  else if (num >= 2 && num <= 8) result = 'S';
  else result = 'H';

  return result;
};

export default getRandomPitchResult;

// 가중치
// ball 0.5
// strike 0.2
// hit 0.3
